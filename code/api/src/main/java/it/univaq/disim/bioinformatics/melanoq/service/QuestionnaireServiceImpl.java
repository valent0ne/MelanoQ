package it.univaq.disim.bioinformatics.melanoq.service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;
import it.univaq.disim.bioinformatics.melanoq.model.Query;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.model.nested.Filter;
import it.univaq.disim.bioinformatics.melanoq.model.section.A1;
import it.univaq.disim.bioinformatics.melanoq.repository.QuestionnaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Value("${spring.couchbase.bucket.name}")
    private String springCouchbaseBucketName;

    @Autowired
    private Bucket bucket;

    private static Logger LOGGER = LoggerFactory.getLogger(QuestionnaireServiceImpl.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    private static final TimeZone tz = TimeZone.getTimeZone("GMT");
    private static final String _class = "'it.univaq.disim.bioinformatics.melanoq.model.Questionnaire'";


    public List<Questionnaire> query(Query q) throws BusinessException{
        // prepare result list
        List<Questionnaire> qlist = new ArrayList<>();

        // build the query
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(String.format("SELECT * FROM %s WHERE _class = %s AND (", springCouchbaseBucketName, _class));

        try{

            LOGGER.info(""+(q.getChildren().get(0).getQuery() != null));
            // add eventual filters to the WHERE clause
            int i = 0;
            for(Filter filter: q.getChildren() ){
                String actualLogicalOperator = Query.logicalOperators.get(q.getLogicalOperator());
                if(i == 0){
                    actualLogicalOperator = "";
                }
                // if numerical values are compared, the value in the filter must be converted to the Number type and the
                // selected operator must be searched in the map of numerical operators
                if(Query.numericOperators.containsKey(filter.getQuery().getSelectedOperator())){

                    queryBuilder.append(String.format(" %s TONUMBER(%s) %s %s", actualLogicalOperator,
                            filter.getQuery().getSelectedOperand(),
                            Query.numericOperators.get(filter.getQuery().getSelectedOperator()),
                            q.toNumber(filter.getQuery().getValue())));
                }
                // Otherwise, if text values are compared, the value in the filter must be converted to the String type
                // and the selected operator must be searched in the map of text operators
                else{
                    String finalValue = filter.getQuery().getValue();
                    if(Query.textOperators.get(filter.getQuery().getSelectedOperator()).contains("LIKE")){
                        finalValue = q.likeWrapper(filter.getQuery().getValue());
                    }
                    queryBuilder.append(String.format(" %s UPPER(%s) %s UPPER(%s)", actualLogicalOperator,
                            filter.getQuery().getSelectedOperand(),
                            Query.textOperators.get(filter.getQuery().getSelectedOperator()),
                            q.toString(finalValue)));
                }
                i++;
            }
        } catch (Exception e){
            //e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.QUERY_NOT_VALID);
        }
        // close the generated clause
        queryBuilder.append(")");

        // run the query
        LOGGER.info("query: {}", queryBuilder.toString());
        N1qlQueryResult result = bucket.query(N1qlQuery.simple(queryBuilder.toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        for (N1qlQueryRow row : result) {
            try{
                // clean the received object by entering in the first node
                JsonNode jnode = objectMapper.readValue(row.value().toString(), JsonNode.class);
                String cleanedJobj = jnode.get(springCouchbaseBucketName).toString();
                LOGGER.info(cleanedJobj);

                qlist.add(objectMapper.readValue(cleanedJobj, Questionnaire.class));
            }catch (IOException ex){
                LOGGER.warn(ex.getMessage());
            }
        }

        // return list of questionnaires
        LOGGER.info("# of results: "+qlist.size());
        return qlist;
    }

    public Questionnaire findOneByDbCodeNumber(String dbCodeNumber){
        LOGGER.info("findOneByDbCodeNumber: dbCodeNUmber = {}", dbCodeNumber);
        Questionnaire q = questionnaireRepository.findOneByDbCodeNumberIgnoreCase(dbCodeNumber);
        if(q == null){
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.DB_CODE_NUMBER_NOT_VALID);
        }
        return q;
    }

    public List<Questionnaire> findAll(){

        List<Questionnaire> q = questionnaireRepository.findAll();

        return q;
    }

    public Questionnaire insert(Questionnaire q) throws BusinessException{
        //if there is no section A1, then there is something wrong
        if(q.getA1() == null){
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.MISSING_A1);
        }

        Date date = new Date(System.currentTimeMillis());
        sdf.setTimeZone(tz);
        if(q.getA1().getDatesOfUpdateQuestionnaire() != null){
            q.getA1().getDatesOfUpdateQuestionnaire().add(sdf.format(date));
        }else{
            List<String> datesOfUpdateQuestionnaire = new ArrayList<>();
            datesOfUpdateQuestionnaire.add(sdf.format(date));
            q.getA1().setDatesOfUpdateQuestionnaire(datesOfUpdateQuestionnaire);
        }

        LOGGER.info("Inserting data to db...");

        return questionnaireRepository.save(q);
    }

    public Questionnaire update(Questionnaire q) throws BusinessException{
        // update does not exists in couchbase
        return this.insert(q);
    }





}

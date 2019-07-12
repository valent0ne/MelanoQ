# MelanoQ

REST-based implementation of the MelanoQ questionnaire https://www.ncbi.nlm.nih.gov/pubmed/30098061

note: remeber to run `CREATE PRIMARY INDEX ON melanoq USING VIEW;` after having created the bucket.


backup: `cbbackup http://host:8091 path-to-dest-folder -u user -p password`
restore: `cbrestore http://host:8091 path-to-source-folder -u user -p password -b bucket-name`
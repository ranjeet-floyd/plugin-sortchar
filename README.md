# Elastic Search plugin sortchar analyzer 
Before indexing sort chars. Name **`analyzer=sort_char`**
## How to Use 
Copy build jar in `<path>/<to>/<elasticsearch>/plugins/{folder}` where `<path>/<to>/<elasticsearch>` is the path to your Elasticsearch installation.
**Create directory with some name eg. sortchar-analyzer**
Since it resides in the plugins directory, it can be used as a plugin right away.
## Running the plugin 
1. Start elasticsearch with e.g. bin/elasticsearch
2. If you do not have any indexes, create one using:

        curl -XPUT 'localhost:9200/test'

3. Test the analyzer:

        curl -XGET 'localhost:9200/test/_analyze?analyzer=sort_char&pretty=true' -d 'This+is++some+text'

    You should see the following response from Elasticsearch:

        {
	    "tokens" : [ {
	    "token" : "++++eehiimossstttx",
	    "start_offset" : 0,
	    "end_offset" : 0,
	    "type" : "word",
	    "position" : 1
	    } ]
	    }


At this point you can use the `sort_char` analyzer just as you would any of the built-in Elasticsearch analyzers.

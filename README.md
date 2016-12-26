# plugin-sortchar  | analyzer=sort_char
Before indexing sort char with default `Arrays.sort(null)`. 
## Installation

Clone this repository and build jar. Copy build jar in `<path>/<to>/<elasticsearch>/plugins` where `<path>/<to>/<elasticsearch>` is the path to your Elasticsearch installation. From `<path>/<to>/<elasticsearch>/plugins/plugin-sortchar`., run the following command:

    $ mkdir plugin-sortchar    # create dir
    $ cd plugin-sortchar & jar xf plugin-sortchar-1.0.jar # go to dir and extract jar class
    # Manually copy  es-plugin.properties file to plugin-sortchar dir.
    # Delete plugin-sortchar-1.0.jar

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
            "token" : "",
            "start_offset" : 0,
            "end_offset" : 0,
            "type" : "word",
            "position" : 1
          }, {
            "token" : "",
            "start_offset" : 0,
            "end_offset" : 0,
            "type" : "word",
            "position" : 2
          }, {
            "token" : "",
            "start_offset" : 0,
            "end_offset" : 0,
            "type" : "word",
            "position" : 3
          }, {
            "token" : "",
            "start_offset" : 0,
            "end_offset" : 0,
            "type" : "word",
            "position" : 4
          }, {
            "token" : "eehiimossstttx",
            "start_offset" : 0,
            "end_offset" : 0,
            "type" : "word",
            "position" : 5
          } ]
        }


At this point you can use the `sort_char` analyzer just as you would any of the built-in Elasticsearch analyzers.

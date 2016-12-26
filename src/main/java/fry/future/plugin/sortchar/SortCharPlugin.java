package fry.future.plugin.sortchar;

import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.AbstractPlugin;

public class SortCharPlugin extends AbstractPlugin {

    /*
     * Set the name that will be assigned to this plugin.
     */
    @Override
    public String name() {
        return "plugin-sortchar";
    }

    /*
     * Return a description of this plugin.
     */
    @Override
    public String description() {
        return "Analyzer to lowercase and  default sort chars of string";
    }

    /*
     * This is the function that will register our analyzer with Elasticsearch.
     */
    public void onModule(AnalysisModule analysisModule) {
        analysisModule.addProcessor(new SortCharBinderProcessor());
    }
}

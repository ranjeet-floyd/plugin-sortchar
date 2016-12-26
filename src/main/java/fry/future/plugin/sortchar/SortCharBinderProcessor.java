package fry.future.plugin.sortchar;

import org.elasticsearch.index.analysis.AnalysisModule;

public class SortCharBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    /*
     * This is the only function that you need. It simply adds our
     * SortChatAnalyzerProvider class
     * to a list of bindings.
     */
    @Override
    public void processAnalyzers(AnalyzersBindings analyzersBindings) {
        analyzersBindings.processAnalyzer(SortChatAnalyzerProvider.NAME,
                SortChatAnalyzerProvider.class);
    }
}

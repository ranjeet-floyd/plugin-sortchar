package fry.future.plugin.sortchar;

import java.io.IOException;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.elasticsearch.index.settings.IndexSettings;
import fry.future.plugin.analysis.sortchar.SortCharAnalyzer;

public class SortChatAnalyzerProvider extends AbstractIndexAnalyzerProvider<SortCharAnalyzer> {

    /*
     * Constructor. Nothing special here.
     */
    @Inject
    public SortChatAnalyzerProvider(Index index, @IndexSettings Settings indexSettings,
            Environment env, @Assisted String name, @Assisted Settings settings) throws IOException {
        super(index, indexSettings, name, settings);
    }

    /*
     * This function needs to be overridden to return an instance of
     * SortCharAnalyzer.
     */
    @Override
    public SortCharAnalyzer get() {
        return this.analyzer;
    }

    /*
     * Instance of SortCharAnalyzer class that is returned by this class.
     */
    protected SortCharAnalyzer analyzer = new SortCharAnalyzer();

    /*
     * Name to associate with this class. We will use this in
     * PlusSignBinderProcessor.
     */
    public static final String NAME = "sort_char";
}

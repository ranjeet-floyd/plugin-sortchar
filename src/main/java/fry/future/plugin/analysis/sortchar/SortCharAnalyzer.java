package fry.future.plugin.analysis.sortchar;

import java.io.Reader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;

public class SortCharAnalyzer extends Analyzer {

    /*
     * This is the only function that we need to override for our analyzer. It
     * takes in a
     * java.io.Reader object and saves the tokenizer and list of token filters
     * that operate
     * on it.
     */
    @Override
    protected TokenStreamComponents createComponents(String field, Reader reader) {
        Tokenizer tokenizer = new SortCharTokenizer(reader);
        TokenStream lowerCaseFilter = new LowerCaseFilter(tokenizer);
        return new TokenStreamComponents(tokenizer, lowerCaseFilter);
    }
}

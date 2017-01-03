package fry.future.plugin.analysis.sortchar;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class SortCharTokenizer extends Tokenizer {
    /*
     * Lucene uses attributes to store information about a single token. For
     * this tokenizer, the
     * only attribute that we are going to use is the CharTermAttribute, which
     * can store the text
     * for the token that is generated. Other types of attributes exist (see
     * interfaces and
     * classes derived from org.apache.lucene.util.Attribute); we will use some
     * of these other
     * attributes when we build our custom token filter. It is important that
     * you register
     * attributes, whatever their type, using the addAttribute() function.
     */

    protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);

    /*
     * This is the important function to override from the Tokenizer class. At
     * each call. It returns
     * true if a new token is generated and false if there are no more tokens
     * remaining.
     */
    @Override
    public boolean incrementToken() throws IOException {

        // Clear anything that is already saved in this.charTermAttribute
        this.charTermAttribute.setEmpty();
        //pass everthing
        if (this.position < this.stringToTokenize.length()) {
            String nextToken = this.stringToTokenize.substring(this.position);
            this.charTermAttribute.append(nextToken);
            this.position = this.stringToTokenize.length();
            return true;
        } // Execute this block if no more tokens exist in the string.
        else {
            return false;
        }
    }

    /*
     * This is the constructor for our custom tokenizer class. It takes all
     * information from a
     * java.io.Reader object and stores it in a string. If you are expecting
     * very large blocks of
     * text, you might want to think about using a buffer and saving chunks from
     * the reader
     * whenever incrementToken() is called. This function throws a
     * RuntimeException when an
     * IOException is found - you can choose how you want to deal with the
     * IOException, but
     * for our purposes, we do not need to try to recover from it.
     */
    public SortCharTokenizer(Reader reader) {
        super(reader);

        int numChars;
        char[] buffer = new char[1024];
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((numChars = reader.read(buffer, 0, buffer.length)) != -1) {
                stringBuilder.append(buffer, 0, numChars);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        char[] stringBuilderChars = stringBuilder.toString().toLowerCase(Locale.US).toCharArray();
        Arrays.sort(stringBuilderChars);
        this.stringToTokenize = new String(stringBuilderChars);

    }

    /*
     * Reset the stored position for this object when reset() is called.
     */
    @Override
    public void reset() throws IOException {
        super.reset();
        this.position = 0;
    }

    /*
     * This object stores the string that we are turning into tokens. We will
     * process its content
     * as we call the incrementToken() function.
     */
    protected String stringToTokenize;

    /*
     * This stores the current position in this.stringToTokenize. We will
     * increment its value as
     * we call the incrementToken() function.
     */
    protected int position = 0;

    private List<String> tokenizeString(Analyzer analyzer, Reader reader) {
        List<String> result = new ArrayList<>();
        try (TokenStream stream = analyzer.tokenStream(null, reader)) {
            stream.reset();
            while (stream.incrementToken()) {
                result.add(stream.getAttribute(CharTermAttribute.class).toString());
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fry.future.plugin.example;

import fry.future.plugin.analysis.sortchar.SortCharAnalyzer;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 *
 * @author ranjeet
 */
public class APP {

    public static void main(String[] args) throws IOException {

        String text = "requirment of analysis++ #";
        Analyzer analyzer = new SortCharAnalyzer();
        List<String> result = tokenString(analyzer, text);
        System.out.println("Custom==>" + result + "\n");

    }

    private static List<String> tokenString(Analyzer analyzer, String str) throws IOException {
        List<String> result = new ArrayList<>();
        TokenStream tokenStream = analyzer.tokenStream("Test", new StringReader(str));
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            result.add(tokenStream.getAttribute(CharTermAttribute.class).toString());
        }
        return result;
    }
}

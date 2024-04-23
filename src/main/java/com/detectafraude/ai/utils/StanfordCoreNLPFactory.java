package com.detectafraude.ai.utils;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.Properties;

public class StanfordCoreNLPFactory {

    public static StanfordCoreNLP createDefaultPipeline() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");

        return new StanfordCoreNLP(props);
    }
}

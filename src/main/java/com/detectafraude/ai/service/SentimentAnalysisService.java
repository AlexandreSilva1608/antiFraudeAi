package com.detectafraude.ai.service;

import com.detectafraude.ai.utils.StanfordCoreNLPFactory;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;


@Service
public class SentimentAnalysisService {
    private StanfordCoreNLP pipeline;

    public SentimentAnalysisService(){
        try {
            this.pipeline = StanfordCoreNLPFactory.createDefaultPipeline();
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }

    public String analyzeSentiment(String text){
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        String sentiment = "";
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
        }

        return sentiment;

    }
}

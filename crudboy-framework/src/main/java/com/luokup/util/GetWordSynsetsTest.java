//package com.luokup.util;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.List;
//
//import edu.mit.jwi.Dictionary;
//import edu.mit.jwi.IDictionary;
//import edu.mit.jwi.item.*;
//
//
//public class GetWordSynsetsTest {
//
//    public static void main(String[] args) throws IOException {
//        String Path = "D:\\Program Files (x86)\\WordNet\\2.1\\dict";
//        File wnDir = new File(Path);
//        URL url = new URL("file", null, Path);
//        IDictionary dict = new Dictionary(url);
//        dict.open();//打开词典
//        getSynonyms(dict); //testing
//    }
//
//
//    public static void getSynonyms(IDictionary dict) {
//        // look up first sense of the word "go"
//        //IIndexWord idxWord2 = dict.
//        IIndexWord idxWord = dict.getIndexWord("make", POS.VERB);
//        IWordID wordID = idxWord.getWordIDs().get(0); // 1st meaning
//        IWord word = dict.getWord(wordID);
//
//        List<IVerbFrame> verbFrames = word.getVerbFrames();
//        for (int i = 0; i < verbFrames.size(); i++) {
//            IVerbFrame iVerbFrame =  verbFrames.get(i);
//            System.out.println(iVerbFrame);
//        }
//
//        ISynset synset = word.getSynset(); //ISynset是一个词的同义词集的接口
//
//        // iterate over words associated with the synset
//        for (IWord w : synset.getWords())
//            System.out.println(w.getLemma());//打印同义词集中的每个同义词
//    }
//}

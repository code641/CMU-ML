import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.print.attribute.IntegerSyntax;

public class Main {
    public static void main(String[] args) throws Exception{
            int status = 10;
            String[] allAttacks = {"normal", "neptune", "warezclient", "ipsweep", "portsweep", "teardrop", "nmap", "satan", "smurf", "pod", "back", "guess_passwd", "ftp_write", "multihop", "rootkit", "buffer_overflow", "imap", "warezmaster", "phf","land", "loadmodule", "spy", "perl", "saint", "mscan", "apache2", "snmpgetattack", "processtable", "httptunnel", "ps", "snmpguess","mailbomb", "named", "sendmail", "xterm", "worm", "xlock", "xsnoop","sqlattack", "udpstorm"};
            String arffFilePath = "/Users/tanavchangal/Desktop/CMU/ids-java/test-20.arff";
            String arffFilePathAll = "/Users/tanavchangal/Desktop/CMU/ids-java/arff_files/" + allAttacks[status] + ".arff";
            String[] modelFilePaths = {"/Users/tanavchangal/Desktop/CMU/ids-java/dl_bayes_BayesNet.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_bayes_NaiveBayes.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_bayes_NaiveBayesMultinomialText.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_bayes_NaiveBayesUpdateable.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_functions_SimpleLogistic.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_functions_SMO.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_lazy_IBk.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_lazy_KStar.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_lazy_LWL.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_AdaBoostM1.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_AttributeSelectedClassifier.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_Bagging.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_ClassificationViaRegression.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_CVParameterSelection.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_FilteredClassifier.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_IterativeClassifierOptimizer.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_LogitBoost.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_MultiClassClassifier.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_MultiClassClassifierUpdateable.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_MultiScheme.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_RandomCommittee.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_RandomizableFilteredClassifier.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_RandomSubSpace.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_Stacking.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_Vote.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_meta_WeightedInstancesHandlerWrapper.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_misc_InputMappedClassifier.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_rules_DecisionTable.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_rules_JRip.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_rules_OneR.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_rules_PART.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_rules_ZeroR.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_trees_DecisionStump.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_trees_HoeffdingTree.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_trees_J48.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_trees_LMT.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_trees_RandomForest.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_trees_RandomTree.model", "/Users/tanavchangal/Desktop/CMU/ids-java/dl_trees_REPTree.model"};
            for (String path: modelFilePaths){
                Server server = new Server(arffFilePath);
                IDS ids = new IDS(path, arffFilePath);
                // runInstanceMultipleTimes(server, ids, 1000, 0, path);
                // runInstanceMultipleTimes(server, ids, 100, 8);
                // runInstanceMultipleTimes(server, ids, 100, 31);
                // runInstanceMultipleTimes(server, ids, 100, 19);
                // runInstanceMultipleTimes(server, ids, 100, 10);
                // runInstanceMultipleTimes(server, ids, 500, 1);
                // runInstanceMultipleTimes(server, ids, 500, 8);
                // runInstanceMultipleTimes(server, ids, 500, 31);
                // runInstanceMultipleTimes(server, ids, 500, 19);
                // runInstanceMultipleTimes(server, ids, 500, 10);
            }
            Server server = new Server(arffFilePathAll);
            IDS ids = new IDS(modelFilePaths[1], arffFilePathAll);
            runAllInstances(server, ids, status, modelFilePaths[1]);
    }
    public static void runInstanceMultipleTimes(Server server, IDS ids, int times, int status, String path){
        String[] allAttacks = {"normal", "neptune", "warezclient", "ipsweep", "portsweep", "teardrop", "nmap", "satan", "smurf", "pod", "back", "guess_passwd", "ftp_write", "multihop", "rootkit", "buffer_overflow", "imap", "warezmaster", "phf","land", "loadmodule", "spy", "perl", "saint", "mscan", "apache2", "snmpgetattack", "processtable", "httptunnel", "ps", "snmpguess","mailbomb", "named", "sendmail", "xterm", "worm", "xlock", "xsnoop","sqlattack", "udpstorm"};
        server.setStatus(status);
        int corr = 0;
        for (int i=0; i<times; i++){
            try{
            weka.core.Instance data = server.generateData();
            if (ids.isPredictionCorrect(data)){
                corr++;
            }
            else{
                ids.resultLabel(data);
            }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        String fn = path.substring(path.lastIndexOf("/") + 1);
        String[] parts = fn.split("_");
        String modelName = parts[parts.length - 1].split("\\.")[0];
        System.out.println("Correct Predictions: "+corr+"/"+times+" for "+allAttacks[status]+" with "+modelName);   
    }

    public static void runAllInstances(Server server, IDS ids, int status, String path){
        String[] allAttacks = {"normal", "neptune", "warezclient", "ipsweep", "portsweep", "teardrop", "nmap", "satan", "smurf", "pod", "back", "guess_passwd", "ftp_write", "multihop", "rootkit", "buffer_overflow", "imap", "warezmaster", "phf","land", "loadmodule", "spy", "perl", "saint", "mscan", "apache2", "snmpgetattack", "processtable", "httptunnel", "ps", "snmpguess","mailbomb", "named", "sendmail", "xterm", "worm", "xlock", "xsnoop","sqlattack", "udpstorm"};
        Map<String, Integer> counts = new LinkedHashMap<String, Integer>();
        Map<String, Integer> corr_counts = new LinkedHashMap<String, Integer>();
        for (String s: allAttacks){
            counts.put(s, 0);
            corr_counts.put(s, 0);
        }
        server.setStatus(status);
        int corr = 0;
        int times = 0;
        try{
            weka.core.Instances data = server.returnDataOfType(status);

            for (int i=0; i<data.numInstances(); i++){
                if (ids.isPredictionCorrect(data.instance(i))){
                    corr++;
                    times++;
                    String result = ids.resultLabel(data.instance(i));
                    String predicted = result.split(",")[1].trim().split(" ")[1];
                    if (corr_counts.containsKey(predicted)){
                        corr_counts.put(predicted, corr_counts.get(predicted)+1);
                    }
                }
                else{
                    String result = ids.resultLabel(data.instance(i));
                    String predicted = result.split(",")[1].trim().split(" ")[1];
                    if (counts.containsKey(predicted)){
                        counts.put(predicted, counts.get(predicted)+1);
                    }
                    times++;
                }
            }
        String fn = path.substring(path.lastIndexOf("/") + 1);
        String[] parts = fn.split("_");
        String modelName = parts[parts.length - 1].split("\\.")[0];
        System.out.println("Correct Predictions: "+corr+"/"+times+ " (" + (double)corr*100/times + ") for "+allAttacks[status]+" with "+modelName);
        System.out.println("Correct Instances:"+corr_counts);
        System.out.println("Incorrect Instances:"+counts);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

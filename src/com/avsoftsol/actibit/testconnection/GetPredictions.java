package com.avsoftsol.actibit.testconnection;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

import java.util.ArrayList;
import java.util.List;

public class GetPredictions {

  public static void main(String[] arg) {

//	  /List<String> predictionsList = new ArrayList<String>();
	  
    ClarifaiClient client = new ClarifaiBuilder("d0b83b266d254931a71247a2c3b12acb")
        .buildSync();

    final List<ClarifaiOutput<Concept>> predictionResults =
        client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get your custom models
            .predict()
            .withInputs(
                ClarifaiInput.forImage("https://i.ytimg.com/vi/6-Si0vLghHE/maxresdefault.jpg"))
            .executeSync()
            .get();
    for(int i=0;i<predictionResults.size();i++){
    	ClarifaiOutput<Concept> clarifaiOutput = predictionResults.get(i);
		
		List<Concept> concepts = clarifaiOutput.data();

		if(concepts != null && concepts.size() > 0) {
			for (int j = 0; j < 5; j++) {
				System.out.println(concepts.get(j).name());
				//predictionsList.add(concepts.get(j).name());
			}
		}
    }
    
  }
}

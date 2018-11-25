package com.avsoftsol.actibit.testconnection;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import clarifai2.dto.prediction.Prediction;

import java.util.ArrayList;
import java.util.List;

public class GetPredictionsCustomModel {

	public static void main(String[] arg) {

		//	  /List<String> predictionsList = new ArrayList<String>();

		ClarifaiClient client = new ClarifaiBuilder("d0b83b266d254931a71247a2c3b12acb")
				.buildSync();

		final List<ClarifaiOutput<Prediction>> predictionResults = client.predict("{model_id}")
				.withInputs(
						ClarifaiInput.forImage("https://samples.clarifai.com/puppy.jpeg")
						)
				.executeSync().get();

		System.out.println("Items detected are:");

		for (int i=0; i< predictionResults.size();i++){
			ClarifaiOutput<Prediction> clarifaiOutput = predictionResults.get(i);
			List<Prediction> predictions = clarifaiOutput.data();
			if(predictions != null && predictions.size()>0){
				for(int j=0;j<predictions.size();j++){
					System.out.println(predictions.get(i).toString()+"-" + predictionResults.get(i).data());
					System.out.println();
				}
			}
		}

		/*for(int i=0;i<predictionResults.size();i++){
    	ClarifaiOutput<Concept> clarifaiOutput = predictionResults.get(i);

		List<Concept> concepts = clarifaiOutput.data();

		if(concepts != null && concepts.size() > 0) {
			for (int j = 0; j < concepts.size(); j++) {
				System.out.println(concepts.get(j).name()+ " - " +concepts.get(j).value());
				System.out.println();
				//predictionsList.add(concepts.get(j).name());
			}
		}
    }*/

	}
}

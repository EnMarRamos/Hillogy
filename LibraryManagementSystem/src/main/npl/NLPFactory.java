package com.hillogy.library.npl;

public class NLPFactory {

	public static NLPQuery getSource(NLPResource nlpResource) {

		if (nlpResource == NLPResource.APACHE) {
			return new ApacheQuery();
		}

		if (nlpResource == NLPResource.STANFORD) {
			return new StanfordQuery();
		}

		return null;
	}
}

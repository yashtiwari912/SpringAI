package com.yashdev.springai.first;

import com.yashdev.springai.first.helper.Helper;
import com.yashdev.springai.first.service.ChatService;
import com.yashdev.springai.first.service.DataLoader;
import com.yashdev.springai.first.service.DataTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FirstApplicationTests {

	@Autowired
	private DataLoader dataLoader;

	@Autowired
	private DataTransformer dataTransformer;

	@Autowired
	private VectorStore vectorStore;

	@Test
	void testDataLoader() {
		var documents = dataLoader.loadDocumentsFromJson();
		System.out.println("Data loaded from JSON Size: "+documents.size());
		documents.forEach(item -> {
			System.out.println(item);
		});


	}

	@Test
	void testPdfDataLoader() {
		List<Document> documents = this.dataLoader.loadDocumentsFromPdf();
		System.out.println(documents.size());
		documents.forEach(item -> {
			System.out.println(item);
			System.out.println("__________________-");
		});

		System.out.println("Read__now going to transform");

		var transformedDocument = this.dataTransformer.transform(documents);
		System.out.println(transformedDocument.size());

//        going to save the data into database

		this.vectorStore.add(transformedDocument);
		System.out.println("Done");


	}



}

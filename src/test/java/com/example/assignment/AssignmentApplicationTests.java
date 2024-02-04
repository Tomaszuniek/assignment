package com.example.assignment;

import com.example.assignment.dtos.TransactionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentApplicationTests {
		@Autowired
		private MockMvc mockMvc;

		@Autowired
		private ObjectMapper objectMapper;

		@Test
		public void testProcessTransactions() throws Exception {
			String listOfTransactions = objectMapper.writeValueAsString(Arrays.asList(
					new TransactionDTO(100, 1L),
					new TransactionDTO(1000, 2L),
					new TransactionDTO(200, 1L)
			));

			mockMvc.perform(post("/processBatch")
							.contentType(MediaType.APPLICATION_JSON)
							.content(listOfTransactions))
					.andExpect(status().isOk())
					.andExpect(content().string("{\"1\":400,\"2\":2750}"));
		}


	@Test
	public void testProcessTransactionsWithIncorrectUser() throws Exception {
		String listOfTransactions = objectMapper.writeValueAsString(Arrays.asList(
				new TransactionDTO(100, 1L),
				new TransactionDTO(1000, 123L),
				new TransactionDTO(200, 1L)
		));



		mockMvc.perform(post("/processBatch")
						.contentType(MediaType.APPLICATION_JSON)
						.content(listOfTransactions))
				.andExpect(status().isNotFound());
	}
	}
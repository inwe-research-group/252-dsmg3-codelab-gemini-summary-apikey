/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetnews.data.gemini.impl
import com.example.jetnews.BuildConfig
import com.example.jetnews.data.gemini.GeminiRepository
import com.example.jetnews.model.Post
import com.google.ai.client.generativeai.GenerativeModel

class GeminiRepositoryImpl: GeminiRepository {    
    val MODEL_NAME ="gemini-2.5-flash"
    val API_KEY =BuildConfig.API_KEY
    // Instantiate GenerativeModel here
    private val generativeModel = null

    override suspend fun summarizePost(post: Post): String? {
        // Implement the summarization with Gemini API
        val model = GenerativeModel(MODEL_NAME, API_KEY)
        // Implement the summarization with Gemini API
        val postString = StringBuilder()
        for (paragraph in post.paragraphs) {
            postString.append(paragraph.text)
        }
        /**
        Resume el siguiente artículo en 4 puntos concisos.
        Asegúrate de que cada punto sea específico, informativo y relevante.
        Devuelve solo los puntos como texto sin formato.
        Usa texto sin formato, no uses Markdown.
        **/
        val prompt =
            "Summarize the following article in 4 concise bullet points. " +
                    "Ensure each bullet point is specific, informative and relevant. " +
                    "Return just the bullet points as plain text. " +
                    "Use plain text, don't use markdown. \n $postString"

        return model.generateContent(prompt).text
    }

}
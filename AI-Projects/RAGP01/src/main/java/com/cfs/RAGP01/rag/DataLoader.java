/*
package com.cfs.RAGP01.rag;


import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class DataLoader {

    private final VectorStore vectorStore;

    public DataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadSampleDocuments()
    {
        List<Document> documentList = List.of(

                // ── PRODUCTS ──────────────────────────────────────────────────────────
                new Document("iPhone 15 Pro costs $999, features A17 Pro chip, 48MP camera, titanium design, USB-C, and 120Hz ProMotion display."),
                new Document("Samsung Galaxy S24 Ultra costs $1299, features Snapdragon 8 Gen 3, 200MP camera, built-in S Pen, and 5000mAh battery."),
                new Document("Sony WH-1000XM5 Headphones cost $349, offer industry-leading noise cancellation, 30-hour battery, and multipoint Bluetooth."),
                new Document("MacBook Pro 14-inch M3 Pro costs $1999, has 18GB unified memory, 512GB SSD, and 18-hour battery life."),
                new Document("Nike Air Max 270 costs $150, features a large Air heel unit, lightweight mesh upper, available in 10+ colorways."),

                // ── LIFESTYLE & WELLNESS ───────────────────────────────────────────────
                new Document("A productive morning routine includes waking at 6 AM, 10 min meditation, 20 min exercise, and a high-protein breakfast."),
                new Document("A beginner home workout includes push-ups, squats, and planks on Monday, lunges and rows on Wednesday, and HIIT on Friday."),
                new Document("Good sleep hygiene means a consistent schedule, no screens 1 hour before bed, no caffeine after 2 PM, and a cool dark room."),
                new Document("Minimalist living means owning only valuable items, following a one-in-one-out rule, and limiting social media to 30 min/day."),

                // ── FOOD & RECIPES ─────────────────────────────────────────────────────
                new Document("Margherita Pizza is made with pizza dough, tomato sauce, fresh mozzarella, and basil, baked at 250°C for 12-15 minutes."),
                new Document("Chicken Tikka Masala uses chicken marinated in yogurt and spices, grilled then simmered in a tomato cream sauce for 20 minutes."),
                new Document("Avocado Toast is made with toasted sourdough, mashed avocado, lemon juice, chili flakes, and an optional poached egg on top."),
                new Document("Mango Lassi is blended from ripe mango, plain yogurt, milk, sugar, and a pinch of cardamom, served chilled."),

                // ── TRAVEL ─────────────────────────────────────────────────────────────
                new Document("Bali, Indonesia is best visited April to October, known for Tanah Lot Temple, Ubud, rice terraces, and Mount Batur trek."),
                new Document("Paris, France is best visited June to August, famous for the Eiffel Tower, Louvre, Montmartre, and fresh croissants."),
                new Document("Tokyo, Japan is best visited during cherry blossom season March to May, known for Shibuya, Senso-ji, sushi, and ramen."),

                // ── TECHNOLOGY ─────────────────────────────────────────────────────────
                new Document("Spring AI integrates AI into Spring Boot apps and supports OpenAI, Claude, and Ollama with RAG, vector stores, and prompt templating."),
                new Document("RAG works by splitting documents into chunks, embedding them as vectors, storing in a vector DB, then retrieving relevant context for the LLM."),
                new Document("Clean code principles include meaningful names, small methods, DRY, avoiding magic numbers, and writing unit tests for all business logic."),

                // ── FINANCE ────────────────────────────────────────────────────────────
                new Document("The 50/30/20 budget rule allocates 50% to needs, 30% to wants, and 20% to savings and debt repayment."),
                new Document("Index funds passively track the market with low fees (0.03%-0.2%) and historically outperform 80-90% of active funds long-term."),
                new Document("Bitcoin is a decentralized digital currency with a 21 million max supply, while Ethereum powers smart contracts and DeFi applications.")

        );

        vectorStore.add(documentList);
        System.out.println("✅ Loaded " + documentList.size() + " documents into the vector store.");
    }
}
*/

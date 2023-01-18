/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agentes;

import GUI.GUIFrame;
import GUI.GUIResultados;
import Services.Post;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;

/**
 *
 * @author Usuario iTC
 */
public class AgenteClasificador2 extends Agent {

    public HttpClient httcliente = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    public String url;
    public URI uri;
    public ArrayList<Post> arrPost = new ArrayList();
    public GUI.GUIResultados guiResultados;

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                guiResultados = new GUIResultados();
                ACLMessage msg = receive();
                if (msg != null) {
                    url = msg.getContent();
                    uri = URI.create(url);
                    guiResultados.llenarTabla(AgenteClasificador2());
                    guiResultados.pack();
                    guiResultados.setVisible(true);
                } else {
                    block();
                }
            }

        });
    }

    public ArrayList<Post> AgenteClasificador2() {
        System.out.println(url);
        //ArrayList<Post> arrPost = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(uri)
                .build();
        try {
            final HttpResponse<String> response = httcliente.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode node = mapper.readTree(response.body());
            for (JsonNode a : node.get("organic_results")) {
                Post p = new Post("Dato no econtrados", "Datos no econtrado", "Datos no econtrados", "Datos no econtrados");
                p.setId(a.get("position").asText() != null ? a.get("position").asText() : "Dato no econtrado");
                p.setTitle(a.get("title").asText() != null ? a.get("title").asText() : "Dato no econtrado");
                p.setSnippet(a.get("snippet").asText() != null ? a.get("snippet").asText() : "Dato no econtrado");
                p.setLink(a.get("link").asText() != null ? a.get("link").asText() : "Dato no econtrado");
                arrPost.add(p);
            }

        } catch (IOException | InterruptedException | NullPointerException ex) {
            Logger.getLogger(AgenteClasificador2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrPost;
    }

}

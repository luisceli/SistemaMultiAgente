/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author LENOVO
 */

public class API {
    /*
    private HttpClient httcliente = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    public String Busqueda = "la biblia";
     
    
    public ArrayList<Post> API() {
        ArrayList<Post> arrPost = new ArrayList();
        Busqueda=Busqueda.replaceAll(" ", "+");
        ObjectMapper mapper = new ObjectMapper();
        final HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create("https://serpapi.com/search.json?engine=google_scholar&q="+Busqueda+"&hl=es&api_key=c1b339cc8366e838747393bf32b2a2f5a7c11256f7221e87a6df657e450bd382"))
                .build();
        try {
            final HttpResponse<String> response = httcliente.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode node = mapper.readTree(response.body());
            for (JsonNode a : node.get("organic_results")) {
                Post p = new Post();
                p.setId(a.get("position").asText());
                p.setTitle(a.get("title").asText());
                p.setSnippet(a.get("snippet").asText());
                p.setLink(a.get("link").asText());
                arrPost.add(p);
            }

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrPost;
    }

    public static void main(String[] args) {
        API A = new API ();
       
        System.out.println(A.API().get(1));
    }
*/
}

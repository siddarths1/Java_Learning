    
    import java.io.IOException;
    
    import com.fasterxml.jackson.core.JsonParseException;
    import com.fasterxml.jackson.core.JsonParser.Feature;
    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.core.TreeNode;
    import com.fasterxml.jackson.databind.JsonMappingException;
    import com.fasterxml.jackson.databind.ObjectMapper;
    
    
    public class MessageParser implements IParser {
    
        private ObjectMapper mapper = null;
    
        public MessageParser() {
            mapper = new ObjectMapper();
            mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        }
    
        public <T> T transformRq(String jsonRq, Class<T> className) {
            // IRequest
            T responseObj = null;
            try {
                responseObj = mapper.readValue(jsonRq, className);
            } catch (JsonParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
            return responseObj;
        }
    
        @Override
        public <T> T transformRq(TreeNode jsonNode, Class<T> className) {
            try {
                return mapper.treeToValue(jsonNode, className);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    
        public String transformRs(Object iResponse) {
            // IResponse
            String response = "";
            try {
                response = mapper.writeValueAsString(iResponse);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
            return response;
        }
    
    }
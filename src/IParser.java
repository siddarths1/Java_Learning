
import com.fasterxml.jackson.core.TreeNode;

public interface IParser {

    public <T> T transformRq(String jsonRq, Class<T> className);

    public <T> T transformRq(TreeNode jsonNode, Class<T> className);

    public String transformRs(Object apiResponse);

}
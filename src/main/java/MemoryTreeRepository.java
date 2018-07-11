import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryTreeRepository implements TreeRepository{

    private static final Map<Object, List<AbstractNode>> NODE_DATA_MAP = new ConcurrentHashMap<>();

    public <T extends AbstractNode> void save(T abstractNode) {
        Object parentId = abstractNode.getParentId();
        if (NODE_DATA_MAP.containsKey(parentId)) {
            NODE_DATA_MAP.get(parentId).add(abstractNode);
        } else {
            NODE_DATA_MAP.put(parentId, Lists.newArrayList(abstractNode));
        }
    }

    public <T extends AbstractNode> List<T> findByParentNode(T node) {
        return (List<T>) NODE_DATA_MAP.get(node.getId());
    }
}

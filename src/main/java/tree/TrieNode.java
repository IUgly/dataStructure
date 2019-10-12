package tree;

/**
 * @author kuangjunlin
 */
class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert (String word) {
        if (word == null) return;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        node.path++;
        int index = 0;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.map[index] == null) {
                node.map[index] = new TrieNode();
            }
            node = node.map[index];
            node.path++;
        }
        node.end++;
    }

    public void delete (String word) {
        if (search(word)) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.path ++;
            int index = 0;
            for (char ch : chs) {
                index = ch - 'a';
                if (node.map[index].path-- == 1) {
                    node.map[index] = null;
                    return;
                }
                node = node.map[index];
            }
            node.end--;
        }
    }

    public int prefixNumber (String pre) {
        if (pre == null) return 0;
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.map[index] == null) {
                return 0;
            }
            node = node.map[index];
        }
        return node.path;
    }

    public boolean search (String word) {
        if (word == null) return false;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.map[index] == null) return false;
            node = node.map[index];
        }
        return node.end != 0;
    }
}

class TrieNode {
    public int path;
    public int end;
    public TrieNode[] map;

    /**
     * path : 多少单词共用此节点
     * end  : 多少单词以这个节点结尾
     * map  : 该节点的一条字符路径  -  字符路径指向的节点
     */
    public TrieNode() {
        this.path = 0;
        this.end = 0;
        this.map = new TrieNode[26];
    }
}

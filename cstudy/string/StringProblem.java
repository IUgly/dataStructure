class Trie {
    private TrieNode root;
    public Trie () {
        root = new TrieNode();
    }

    public void insert (String s) {
        if (s == null || s.equals("")) return;
        char[] chs = s.toCharArray();
        TrieNode node = root;
        node.path++;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.map[index] == null) 
                node.map[index] = new TrieNode();
            node = node.map[index];
            node.path++;
        }
        node.end++;
    }

    public void delete (String s) {
        if (search(s)) {
            char[] chs = s.toCharArray();
            TrieNode node = root;
            node.path++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                inde = chas[i] - 'a';
                if (node.map[index].path-- == 1) {
                    node.map[index] = null;
                    return;
                }
                node = node.map[index];
            }
            node.end --;
        }
    }

    public boolean search (String s) {
        if (word == null) return false;
        char[] chs = s.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.map[index] == null) 
                return false;
            node = node.map[index];
        }
        return node.end != 0;
    }

    int prefixNumber (String s) {
        if (pre == null) return 0;
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0 ;i < chs.length ; i++) {
            index = chs[i] - 'a';
            if (node.map[index] == null) return 0;
            node = node.map[index];
        }
        return node.path;
    }
}

class TrieNode {
    int path;
    int end;
    TrieNode[] map;

    public TrieNode () {
        path = 0;   //共用这个节点的字符数
        end = 0;    //以该节点为结尾的字符数
        map = new TrieNode[26]; //该节点的一条字符路径 - 字符路径指向的节点
    }
}
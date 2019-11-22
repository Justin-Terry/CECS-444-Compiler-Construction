package tree;

import symbol_table.ScopeTree;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    public Node root;

    public Tree() {

    }

    public void printTree(){
        print(this.root);
    }

    public void print(Node n){
        if(n == null){
            return;
        }else{
            for(Node lk : n.getLeftChildren()){
                print(lk);
            }
            System.out.println(n);
            for(Node rk : n.getRightChildren()){
                print(rk);
            }
        }
    }

    public void insertNode(Node n) {
        if(this.root == null) {
            System.out.println("SETTING ROOT");
            this.root = n;
        }else{
            Node parent = findNode(root, n.getParentId());
            if(n.getSide() == 'l'){
                parent.getLeftChildren().add(n);
                n.setParent(parent);
            } else{
                parent.getRightChildren().add(n);
                n.setParent(parent);
            }
        }
    }

    public Node findNode(Node n, String id) {
        if(n.getId().equals(id)){
            return n;
        }else if(n.getLeftChildren().size() > 0){
            for(Node ns : n.getLeftChildren()) {
                Node node = findNode(ns, id);
                if(node != null){
                    return node;
                }
            }
            return null;
        }else if(n.getRightChildren().size() > 0){
            for(Node ns : n.getRightChildren()) {
                Node node = findNode(ns, id);
                if(node != null){
                    return node;
                }
            }
            return null;
        }else{
            return null;
        }
    }
}

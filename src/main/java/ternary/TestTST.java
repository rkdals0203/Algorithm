package main.java.ternary;

public class TestTST {

        public static void main(String[] args) {

        TSTree t = new TSTree();

        Itm itm = new Itm();

        t.insert("cat");

        t.insert("cats");

        t.insert("up");

        t.insert("bug");



        System.out.println(

        "Following is traversal of ternary search tree:");

        t.traverseTST();



        System.out.println(

        "\nFollowing are search results for 'cats', 'bu', and 'up':");

        System.out.println("cats: "+(itm.searchTST(t, "cats")?"Found": "Not Found"));

        System.out.println("bu: "+(itm.searchTST(t, "bu")?"Found": "Not Found"));

        System.out.println("up: "+(itm.searchTST(t, "up")?"Found": "Not Found"));

        }



        }

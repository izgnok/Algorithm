import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;


class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());

        for(int i=0; i<L; i++){
            String input = br.readLine();
            LinkedList<Character> list = new LinkedList<>();
            ListIterator iter = list.listIterator();

            for(int j=0; j<input.length(); j++){
                char c = input.charAt(j);
                if(c == '<' && iter.hasPrevious()){
                    iter.previous();
                } else if(c=='>' && iter.hasNext()){
                    iter.next();
                } else if(c == '-' && iter.hasPrevious()){
                    iter.previous();
                    iter.remove();

                } else if(isLetter(c)){
                    iter.add(c);
                }

            }
            StringBuilder sb = new StringBuilder();
            for(char next : list){
                if(next != '#'){
                    sb.append(next);
                }
            }
            System.out.println(sb.toString());

        }

    }

    public static boolean isLetter(char c){
        if((c>='A' && c<='Z')
        || (c>='a' && c<='z')
        || (c>='0' && c<='9')){
            return true;
        }
        return false;
    }

}
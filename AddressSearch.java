package Final;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		
		//↓CSVのデータを読み込む(クラスかしてもいいかも)
		
		String csvFile = "src/Final/郵便番号データ.csv"; // 読み込むCSVファイルのパスを指定
        String line;
//        String delimiter = ","; // CSVファイルの区切り文字
        
        
        
//        読み込みと表示↓（一旦コメントアウト）
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                for (String value : values) {
//                    System.out.println(value); // 読み取ったデータを表示
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace(); // エラーメッセージを表示
//        }
        
        //↓郵便番号検索か住所検索かの分岐(クラスかしてもいいかも)
          
        
        Scanner scanner = new Scanner(System.in);
        String input = "";
        
      //再度入力を求めるためのwhile文
         while (true) {
        
        		while (true) {
        			System.out.println("検索条件を選択(1･･･郵便番号/2･･･住所)：");
        			input =scanner.nextLine();
        	
        	
        			if ("1".equalsIgnoreCase(input)) {
        				System.out.println("郵便番号検索クラスが入る予定");
        				
        				PostSearch postSearch = new PostSearch();
        	            postSearch.executeSearch(); 
        				
        				
        				//↓Sortクラスデータを表示するクラスを入れる
                		
                		
            			//postのデータか住所のデータをでヒットしたものを受け取る。    		
            		
            		
        				//↑Sortクラスデータを表示するクラス
        	            System.out.println("検索が終了しました。");
        				
        				break;
        			} else if ("2".equalsIgnoreCase(input)) {
        				System.out.println("住所検索クラスが入る予定");
        				
        				AddressSearch Address = new AddressSearch();
        				Address.executeSearch();
        				
        				//↓Sortクラスデータを表示するクラスを入れる
                		
                		   		
            		
        				//↑Sortクラスデータを表示するクラス
        				  				
        				break;
        			} else {
        				System.out.println("無効な入力です。1 または 2 を選択してください。");
        			}
           
        		}
        	//いらんかも↓
        		//↓Sortクラスデータを表示するクラスを入れる
        		
        		
        			//postのデータか住所のデータをでヒットしたものを受け取る。    		
        		
        		
        		//↑Sortクラスデータを表示するクラス
        	//いらんかも↑
             

        	//↓もう一度検索するかどうかを聞きyesならまた郵便検索か住所検索かまで戻る
        	while (true) { 
        		
        		System.out.println("新たな条件で検索しますか？(y/n)：");
        		String reinput = "";
        		reinput =scanner.nextLine();  		

        		if ("y".equalsIgnoreCase(reinput)) {
        			System.out.println("続ける");
        			break;
        		} else if ("n".equalsIgnoreCase(reinput)) {
        			System.out.println("続けない");
        			scanner.close();
        			return;
        		} else {
        			System.out.println("無効な入力です。y または n を選択してください。");

        		}
        
        	}       	
        	
        }
        
	}

}

package Final;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//検索してヒットしたものを出力する処理を書きたい。


public class PostSerach {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int number = 0;
        boolean isValid = false;

        // 有効な入力が得られるまで繰り返す
        while (!isValid) {
            System.out.print("7文字までの整数を入力してください: ");
            input = scanner.nextLine();

            if (validateInput(input)) {
                try {
                    number = Integer.parseInt(input);
                    isValid = true; // 有効な場合ループ終了
                } catch (NumberFormatException e) {
                	//このケースを考える必要はないかも↓（あまりに発生しずらいケースなので）
                    System.out.println("無効な数値です。整数値を入力してください。");
                }
            } else {
                System.out.println("入力は7文字以下の数字である必要があります。");
            }
        }
        
        
        //number(入力された値であり、これを使って前方一致で検索する。)
        
        //郵便番号(7桁)で検索した場合は、指定した郵便番号(7桁)を前方一致で検索し、検索結果を
        //郵便番号(7桁)順に表示します。ただし、郵便番号(7桁)が同一の場合は住所カナの昇順に表示します。
        
       
        
     // ダミーデータのためのクラス
         class PostData {
            String postCode; // 郵便番号
            String addressKana; // 住所カナ

            // コンストラクタ
            public PostData(String postCode, String addressKana) {
                this.postCode = postCode;
                this.addressKana = addressKana;
            }
        }
    
        
     // 仮の郵便番号データ
        List<PostData> postDataList = new ArrayList<>();
        postDataList.add(new PostData("1234567", "カナザワシ"));
        postDataList.add(new PostData("1234500", "カナザワチョウ"));
        postDataList.add(new PostData("1234561", "ナカノシ"));
        postDataList.add(new PostData("4561234", "オオサカシ"));
        postDataList.add(new PostData("1234000", "アオモリシ"));

        // 検索条件に合致するデータをフィルタリング
        String searchKey = String.valueOf(number); // 検索用文字列
        List<PostData> results = new ArrayList<>();
        for (PostData data : postDataList) {
            if (data.postCode.startsWith(searchKey)) { // 前方一致
                results.add(data);
            }
        }

        // ソート: 郵便番号の昇順 → 住所カナの昇順
//        Collections.sort(results, Comparator
//                .comparing((PostData data) -> data.postCode)
//                .thenComparing(data -> data.addressKana));

        // 結果を表示
        if (results.isEmpty()) {
            System.out.println("検索条件に一致するデータはありませんでした。");
        } else {
            System.out.println("検索結果:");
            for (PostData data : results) {
                System.out.println("郵便番号: " + data.postCode + ", 住所: " + data.addressKana);
            }
        }

        System.out.println("入力された有効な数値: " + number);
        
      
    }

	
	
	//ヴァリデーションをかけた。
    public static boolean validateInput(String input) {
        return input.matches("\\d{1,7}"); // 1から7文字の数字ならtrueを返す。
    }
	
	
	
	
}

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
        
       
        
     // ↓ダミーデータのためのクラス(データを受け取るクラスとして採用予定)
         class PostData {
        	Integer localNum; //全国地方公共団体コード
        	Integer oldPostCode; // (旧)郵便番号(5桁)
        	Integer postCode; // 郵便番号(7桁)
            
            String Prefecture; //都道府県名（全角カタカナ）
            String City; // 市区町村名(全角カタカナ)
            String Town;//町域名（全角カタカナ）

            String preKanji; //都道府県名（漢字）
            String cityKanji; //市区町村名（漢字）
            String townKanji; //町域名（漢字）
            

            // コンストラクタ
            public PostData(Integer localNum, Integer oldPostCode, Integer postCode, String Prefecture, String City, String Town, String preKanji, String cityKanji, String townKanji) {
                this.localNum = localNum;
                this.oldPostCode = oldPostCode;
                this.postCode = postCode;
                this.Prefecture = Prefecture;
                this.City = City;
                this.Town = Town;
                this.preKanji = preKanji;
                this.cityKanji = cityKanji;
                this.townKanji = townKanji; 
            }
        }
    
        
     // 仮の郵便番号データ
      // ダミーデータを返す静的メソッド
         public static List<PostData> getDummyData() {
        	    List<PostData> dummyData = new ArrayList<>();
        	    dummyData.add(new PostData(1001, 12345, 1234567, "ミヤギケン", "センダイシ", "アオバク", "宮城県", "仙台市", "青葉区"));
        	    dummyData.add(new PostData(1002, 54321, 7654321, "トウキョウト", "シブヤク", "ヒカリエ", "東京都", "渋谷区", "ヒカリエ"));
        	    dummyData.add(new PostData(1003, 11111, 1010101, "ホッカイドウ", "サッポロシ", "チュウオウク", "北海道", "札幌市", "中央区"));
        	    dummyData.add(new PostData(1004, 22222, 2020202, "オオサカフ", "オオサカシ", "キタク", "大阪府", "大阪市", "北区"));
        	    dummyData.add(new PostData(1005, 33333, 3030303, "フクオカケン", "フクオカシ", "ハカタク", "福岡県", "福岡市", "博多区"));
        	    return dummyData;
        }
         
         // ↑ダミーデータのためのクラス(データを受け取るクラスとして採用予定)
         
         

        // 検索条件に合致するデータをフィルタリング
        String searchKey = String.valueOf(number); // 検索用文字列
        List<PostData> results = new ArrayList<>();
        for (PostData data : dummyData) {
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
            	System.out.println("郵便番号: " + data.postCode + ", 住所: " + data.preKanji + " " + data.cityKanji + " " + data.townKanji);
            }
        }

        System.out.println("入力された有効な数値: " + number);
        
      
    }

	
	
	//ヴァリデーションをかけた。
    public static boolean validateInput(String input) {
        return input.matches("\\d{1,7}"); // 1から7文字の数字ならtrueを返す。
    }
	
	
	
	
}

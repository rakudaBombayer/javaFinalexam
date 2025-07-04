package Final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PostSearch {

    // 検索処理を行うインスタンスメソッド
    public void executeSearch() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean isValid = false;
        boolean isValid2 = false;
        
        // 有効な入力を得られるまで繰り返す
        while (!isValid) {
            System.out.print("郵便番号を7文字までの整数で入力してください: ");
            input = scanner.nextLine();

            if (validateInput(input)) {
                isValid = true; // 有効な場合ループ終了
            } else {
                System.out.println("入力は7文字以下の数字である必要があります。");
            }
        }

        // CSVデータを取得
        List<PostData> csvData = getCSVData();

        // 検索条件に合致するデータをフィルタリング
        String searchKey = input; // 検索用文字列
        List<PostData> results = new ArrayList<>();
        for (PostData data : csvData) {
            if (data.postCode.startsWith(searchKey)) { // 前方一致
                results.add(data);
            }
        }
        
        
     // 検索結果を確認
      if (results.isEmpty()) { // リストが空の場合
           System.out.println("該当なし");
      } else {
        	
       //ヒットしたものがあったときの処理を書くところ
       
        
        
        
        
        
        
        // 複数条件でソート
        Collections.sort(csvData, Comparator
                .comparing((PostData data) -> data.Prefecture) // Prefectureで昇順ソート
                .thenComparing(data -> data.City)             // Cityで昇順ソート
                .thenComparing(data -> data.Town)             // Townで昇順ソート
            );
        
        
        Integer pageSize = 1; //グローバーるに使うため、外に出した。(除算で0のエラーが出たため、とりあえず1にした。)
        
        while (!isValid2) {
        	System.out.print("表示する件数を入力してください: ");
            String inputPage = scanner.nextLine();
            
            
            if (validateNum(inputPage) ) {
            	pageSize = Integer.parseInt(inputPage);
                isValid2 = true; // 有効な場合ループ終了
            } else {
                System.out.println("数値で入力してください。");
            }
        }
        
     
        int totalResults = results.size(); // 結果の総件数
        
        

        for (int i = 0; i < totalResults; i++) {
            // 現在のデータを表示
            System.out.println("郵便番号: " + results.get(i).postCode + ", 住所: " + results.get(i).preKanji + " " + results.get(i).cityKanji + " " + results.get(i).townKanji);

            // 入力した件ごとに区切りを追加
            
            int currentPage = (i / pageSize) + 1; // 現在のページ数
            int start = ((currentPage - 1) * pageSize) + 1; // 開始番号
            int end = Math.min(currentPage * pageSize, totalResults); // 終了番号
            
            if ((i + 1) % pageSize == 0 || i == totalResults - 1) {
                System.out.println("----------------------------------------");
                System.out.println("表示件数"+ start + "～" + end +"検索件数：" + totalResults);
                
                if( totalResults <= pageSize) {
//                	System.out.println("★★★★★★★★★★★★★★★★★★★★★");
                	break;
                }
                
                //最後のページ数だけ+1されていない
             // ユーザー入力で続きを表示するかどうか判断
             
                
              System.out.println("表示を継続しますか？(y/n):");
                
	          boolean validInput = false; // 入力が有効かどうかを判定するフラグ
	          while (!validInput) {
	            String userInput = scanner.nextLine().trim(); // 入力を取得してトリム（余計な空白を除去）
	                if (userInput.equalsIgnoreCase("y")) {
	                        System.out.println("次のページを表示します...");
	                        validInput = true; // 入力が有効
	                 } else if (userInput.equalsIgnoreCase("n")) {
	                        System.out.println("表示を終了します。");
	                        return; // プログラム全体を終了
	                 } else {
	                        System.out.println("無効な入力です。続行する場合は 'y'、終了する場合は 'n' を入力してください。");
	                 }
	            }
            }
        }
        
      } //該当なしの閉じタグ 
        
        
        
        
        
        
        
        // 結果を表示
//        if (results.isEmpty()) {
//            System.out.println("検索条件に一致するデータはありませんでした。");
//        } else {
//            System.out.println("検索結果:");
//            for (PostData data : results) {
//                System.out.println("郵便番号: " + data.postCode + ", 住所: " + data.preKanji + " " + data.cityKanji + " " + data.townKanji);
//            }
//        }

//        scanner.close();
    }

    // CSVデータをリストに格納する静的メソッド
    public static List<PostData> getCSVData() {
        List<PostData> csvData = new ArrayList<>();
        String csvFile = "src/Final/郵便番号データ.csv"; // 読み込むCSVファイルのパスを指定
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // CSVの各行を分割して PostData オブジェクトに変換
                String[] values = line.split(",");
                if (values.length >= 9) { // 必須項目数を確認（エラー防止）
                    PostData data = new PostData(
                        values[0], 
                        values[1], 
                        values[2], 
                        values[3], 
                        values[4], 
                        values[5], 
                        values[6], 
                        values[7], 
                        values[8]
                    );
                    csvData.add(data); // リストに追加
                } else {
                    System.out.println("不正なデータ行が検出されました: " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // エラーが発生した場合の処理
        }

        return csvData;
    }

    // ヴァリデーションをかける静的メソッド
    public static boolean validateInput(String input) {
        return input.matches("\\d{1,7}"); // 1から7文字の数字ならtrueを返す。
    }
    
    public static boolean validateNum(String input) {
        return input.matches("[0-9]+"); // 1から7文字の数字ならtrueを返す。
    }
}

package Final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressSearch {
	
	
	//指定した都道府県名を住所漢字１に完全一致で、市区町村名を住所漢字２～３に前方一致で検索
	
	
    // 検索処理を行うインスタンスメソッド
    public void executeSearch() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        String input2 = "";
        boolean isValid = false;

        // 有効な入力を得られるまで繰り返す
        while (!isValid) {
            System.out.print("検索する都道府県を入力(漢字): ");
            input = scanner.nextLine();
            
            System.out.print("検索する市区町村を入力(漢字): ");
            input2 = scanner.nextLine();
            
            if (validateInput(input) && validateInput(input2)) {
                isValid = true; // 有効な場合ループ終了
            } else {
                System.out.println("日本語で入力してください。");
            }
        }

        // CSVデータを取得
        List<PostData> csvData = getCSVData();

        // 検索条件に合致するデータをフィルタリング
        String searchPreKey = input; // 検索用文字列
        String searchCityKey = input2;
        List<PostData> results = new ArrayList<>();
        
        for (PostData data : csvData) {
        	// 完全一致と前方一致
            if (data.postCode.equals(searchPreKey) && data.postCode.startsWith(searchCityKey.substring(0, Math.min(searchCityKey.length(), 3) )))
            { 
                results.add(data);
            }
        }

        // 結果を表示
        if (results.isEmpty()) {
            System.out.println("該当なし");
        } else {
            System.out.println("検索結果:");
            for (PostData data : results) {
                System.out.println("郵便番号: " + data.postCode + ", 住所: " + data.preKanji + " " + data.cityKanji + " " + data.townKanji);
            }
        }

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
            }
        } catch (Exception e) {
            e.printStackTrace(); // エラーが発生した場合の処理
        }

        return csvData;
    }

    // ヴァリデーションをかける静的メソッド
    public static boolean validateInput(String input) {
        return input.matches("[\\u3040-\\u30FF\\u4E00-\\u9FFF]+"); // 1から7文字の数字ならtrueを返す。
    }
}

package Final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean isValid = false;

        // 有効な入力を得られるまで繰り返す
        while (!isValid) {
            System.out.print("7文字までの整数を入力してください: ");
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

        // 結果を表示
        if (results.isEmpty()) {
            System.out.println("検索条件に一致するデータはありませんでした。");
        } else {
            System.out.println("検索結果:");
            for (PostData data : results) {
                System.out.println("郵便番号: " + data.postCode + ", 住所: " + data.preKanji + " " + data.cityKanji + " " + data.townKanji);
            }
        }

        scanner.close();
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

    // ヴァリデーション数字1~7でかけてみた。。
    public static boolean validateInput(String input) {
        return input.matches("\\d{1,7}"); // 1から7文字の数字ならtrueを返す。
    }
}

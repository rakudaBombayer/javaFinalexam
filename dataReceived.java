package Final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PostData {

    String localNum; // 全国地方公共団体コード
    String oldPostCode; // (旧)郵便番号(5桁)
    String postCode; // 郵便番号(7桁)
    String Prefecture; // 都道府県名（全角カタカナ）
    String City; // 市区町村名（全角カタカナ）
    String Town; // 町域名（全角カタカナ）
    String preKanji; // 都道府県名（漢字）
    String cityKanji; // 市区町村名（漢字）
    String townKanji; // 町域名（漢字）

    // コンストラクタ
    public PostData(String localNum, String oldPostCode, String postCode, String Prefecture, String City, String Town, String preKanji, String cityKanji, String townKanji) {
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

    // ダミーデータを生成する静的メソッド
    public static List<PostData> getDummyData() {
        List<PostData> dummyData = new ArrayList<>();
        String csvFile = "src/Final/郵便番号データ.csv"; // 読み込むCSVファイルのパスを指定
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // CSVの各行を分割して PostData オブジェクトに変換
                String[] values = line.split(",");
                
                    PostData data = new PostData(
                        values[0], // localNum（文字列として処理）
                        values[1], // oldPostCode
                        values[2], // postCode
                        values[3], // Prefecture
                        values[4], // City
                        values[5], // Town
                        values[6], // preKanji
                        values[7], // cityKanji
                        values[8]  // townKanji
                    );
                    dummyData.add(data); // リストに追加
                
            }
        } catch (Exception e) {
            e.printStackTrace(); // エラーが発生した場合の処理
        }

        return dummyData;
    }



    // メインメソッド
    public static void main(String[] args) {
        // ダミーデータを取得
        List<PostData> postDataList = PostData.getDummyData();

         //ダミーデータを表示
        for (PostData data : postDataList) {
        	System.out.println("全国地方公共団体コード: " + data.localNum + ", (旧)郵便番号: " + data.oldPostCode + ", 郵便番号: " + data.postCode + ", 都道府県名（カタカナ）: " + data.Prefecture + ", 市区町村名（カタカナ）: " + data.City + ", 町域名（カタカナ）: " + data.Town + ", 都道府県名（漢字）: " + data.preKanji + ", 市区町村名（漢字）: " + data.cityKanji + ", 町域名（漢字）: " + data.townKanji);
            System.out.println("----------------------------------------");
        }
    }
}

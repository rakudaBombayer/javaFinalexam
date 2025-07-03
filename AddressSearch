package Final;

import java.util.Scanner;

public class AddressSearch {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String prefectureName = "";
        String cityName = "";
        boolean isValidPrefecture = false;
        boolean isValidCity = false;

        
        // 都道府県名の入力
        while (!isValidPrefecture) {
            System.out.print("都道府県名を日本語で入力してください: ");
            prefectureName = scanner.nextLine();

            if (validatePrefecture(prefectureName)) {
                isValidPrefecture = true; // 有効な場合ループ終了
            } else {
                System.out.println("都道府県名は日本語で入力してください。再度お試しください。");
            }
        }

        // 市区町村名の入力
        while (!isValidCity) {
            System.out.print("市区町村名を日本語で入力してください: ");
            cityName = scanner.nextLine();

            if (validateCity(cityName)) {
                isValidCity = true; // 有効な場合ループ終了
            } else {
                System.out.println("市区町村名は日本語で入力してください。再度お試しください。");
            }
        }

        System.out.println("入力された都道府県名: " + prefectureName);
        System.out.println("入力された市区町村名: " + cityName);
        
        
        
        
        //prefectureNameは完全一致、cityNameは住所漢字２～３に前方一致で検索
      
        // 住所で検索した場合は、指定した都道府県名を住所漢字１に完全一致で、市区町村名を
        //住所漢字２～３に前方一致で検索し、検索結果を住所カナ１～３の昇順に表示します。
        //画面表示は「郵便番号(7桁)：住所漢字」の形式とします。

        
    }

    // 都道府県名のヴァリデーション
    public static boolean validatePrefecture(String input) {
        return input.matches("[\\p{IsHan}\\p{IsHiragana}\\p{IsKatakana}ー]+"); //　文字のみ
    }

    // 市区町村名のヴァリデーション
    public static boolean validateCity(String input) {
        return input.matches("[\\p{IsHan}\\p{IsHiragana}\\p{IsKatakana}ー]+"); // 文字のみｄ
    
    }
}

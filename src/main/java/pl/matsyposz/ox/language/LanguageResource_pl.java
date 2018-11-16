package pl.matsyposz.ox.language;

import java.util.ListResourceBundle;

@SuppressWarnings("WeakerAccess")
public class LanguageResource_pl extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"description","Gra kółko i krzyżyk. \n" +
                        "Gra składa się z trzech meczy i gracz z największą ilością punktów wygrywa.\n" +
                        "Wygrany mecz wart jest 3 punkty, a remis 1 punkt dla każdego gracza.\n" +
                        "Należy wprowadzać kolejne ruchy w poprawnym formacie:\n" +
                        "'x y' gdzie x to numer kolumny od 0 do szerokości mapy - 1,\n" +
                        "a y to numer wiersza od 0 do wysokości mapy - 1\n" +
                        "Można zakończyć grę wpisując podczas meczu '/exit'.\n"
                },
                {"input",
                        "Proszę wprowadzić rozmiar mapy w formacie 'szerokość wysokość'\n" +
                        "lub wcisnąć enter aby ustawić mape na domyślny rozmiar 3x3.\n" +
                                "Maksymalny rozmiar szerokości/wysokości to 200\n"},
                {"move"," podaje ruch:\n"},
                {"wrongMove","Ten ruch jest niepoprawny, proszę podać inną pozycję:\n"},
                {"matchWin","Wygrywa "},
                {"matchDraw","Mecz kończy się remisem.\n"},
                {"gameWin"," wygrywa gre, gratulacje!\n"},
                {"gameDraw","Gra kończy się remisem.\n"},
                {"nextMatch","Rozpoczęcie kolejnego meczu...\n"},
                {"moveBeyondMap","Nie możesz poruszać się poza granice mapy, proszę wprowadź numer od 0 do szerokości/wysokości mapy - 1\n"},
                {"userName","Proszę podać nazwę gracza:\n"},
        };
    }
}
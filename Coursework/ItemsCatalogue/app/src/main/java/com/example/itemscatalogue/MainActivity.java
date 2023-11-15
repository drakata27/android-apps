package com.example.itemscatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.itemscatalogue.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // binding
        com.example.itemscatalogue.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initialise intent
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        String[] details = new String[] {
                "Arsenal Football Club is an English professional football club based in Holloway, North London. Arsenal compete in the Premier League, the top flight of English football. The club has won 13 league titles (including one unbeaten title), a record 14 FA Cups, two League Cups, 17 FA Community Shields, the Football League Centenary Trophy, one European Cup Winners' Cup and one Inter-Cities Fairs Cup. In terms of trophies won, it is the third-most successful club in English football.",
                "Chelsea Football Club is an English professional football club based in Fulham, West London. Founded in 1905, the team play their home games at Stamford Bridge. The club competes in the Premier League, the top division of English football. It won its first major honour, the League championship, in 1955. The club won the FA Cup for the first time in 1970, their first European honour, the Cup Winners' Cup, in 1971, and became the third English club to win the Club World Cup in 2022.",
                "Liverpool Football Club is a professional football club based in Liverpool, England. The club competes in the Premier League, the top tier of English football. Founded in 1892, the club joined the Football League the following year and has played its home games at Anfield since its formation.",
                "Manchester City Football Club is an English professional football club based in Manchester that competes in the Premier League, the top flight of English football. Founded in 1880 as St. Mark's (West Gorton), they became Ardwick Association Football Club in 1887 and Manchester City in 1894. The club's home ground is the City of Manchester Stadium in east Manchester, to which they moved in 2003, having played at Maine Road since 1923. Manchester City adopted their sky blue home shirts in 1894, in the first season with the current name.[4] Over the course of its history, the club has won nine league titles, seven FA Cups, eight League Cups, six FA Community Shields, one UEFA Champions League, one European Cup Winners' Cup, and one UEFA Super Cup.",
                "Manchester United Football Club, commonly referred to as Man United (often stylised as Man Utd), or simply United, is a professional football club based in Old Trafford, Greater Manchester, England. The club competes in the Premier League, the top division in the English football league system. Nicknamed the Red Devils, they were founded as Newton Heath LYR Football Club in 1878, but changed their name to Manchester United in 1902. After a spell playing in Clayton, Manchester, the club moved to their current stadium, Old Trafford, in 1910.",
                "Tottenham Hotspur Football Club, commonly referred to as simply Tottenham or Spurs, is a professional football club based in Tottenham, London, England. It competes in the Premier League, the top flight of English football. The team has played its home matches in the 62,850-capacity Tottenham Hotspur Stadium since April 2019, replacing their former home of White Hart Lane, which had been demolished to make way for the new stadium on the same site."
        };

        binding.arsenal.setOnClickListener(v -> passData(intent, R.drawable.arsenal, details[0]));

        binding.chelsea.setOnClickListener(v -> passData(intent, R.drawable.chelsea, details[1]));

        binding.lfc.setOnClickListener(v -> passData(intent, R.drawable.lfc, details[2]));

        binding.manCity.setOnClickListener(v -> passData(intent, R.drawable.man_city, details[3]));

        binding.manUtd.setOnClickListener(v -> passData(intent, R.drawable.man_utd, details[4]));

        binding.spurs.setOnClickListener(v -> passData(intent, R.drawable.spurs, details[5]));

    }
    public void passData(Intent intent, int img , String details) {
        intent.putExtra("key", details);
        intent.putExtra("imageRes", img);
        startActivity(intent);
    }
}
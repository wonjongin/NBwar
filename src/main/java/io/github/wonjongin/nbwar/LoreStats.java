package io.github.wonjongin.nbwar;

import lombok.*;
import lombok.experimental.Delegate;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.wonjongin.nbwar.ControlLoreStats.initLoreStats;
import static org.bukkit.Bukkit.getLogger;

@Data
@ToString
@EqualsAndHashCode
@Getter
@Setter
@RequiredArgsConstructor
public class LoreStats {
    /*
    0. uuid
    1. 공격력
    2. 크리티컬
    3. 크리 데미지
    4. 체려
    5. 피흡
    6. 방어력
    7. 방무
     */
    private UUID uuid = UUID.randomUUID();
    private int power = 0; // 공격력
    private int criticalPercent = 0; // 크리 확률
    private int critical = 0; // 크리 데미지
    private int health = 0; // 체력
    private int drain = 0; // 피흡
    private int defend = 0; // 방어력
    private int ignoreDefend = 0; // 방어력 무시
    private String uuidStr = uuid.toString();
    private String powerString = String.valueOf(this.power); // 공격력
    private String criticalPercentString = String.valueOf(this.criticalPercent);
    ; // 크리 확률
    private String criticalString = String.valueOf(this.critical);
    ; // 크리 데미지
    private String healthString = String.valueOf(this.health);
    ; // 체력
    private String drainString = String.valueOf(this.drain);
    ; // 피흡
    private String defendString = String.valueOf(this.defend);
    ; // 방어력
    private String ignoreDefendString = String.valueOf(this.ignoreDefend);
    ; // 방어력 무시

    private ArrayList<String> statsNames = new ArrayList<>(Arrays.asList(
            "공격력",
            "크리확률",
            "크리데미지",
            "체력",
            "피흡",
            "방어력",
            "방어력무시"
    ));
    private ArrayList<Integer> allLore = new ArrayList<>(Arrays.asList(
            this.power,
            this.criticalPercent,
            this.critical,
            this.health,
            this.drain,
            this.defend,
            this.ignoreDefend
    ));

    public LoreStats parseToLoreStats(ItemStack item) {
        Pattern patternNum = Pattern.compile("^[0-9|\\.]*$");
        ItemMeta itemMeta = item.getItemMeta();
        if (!itemMeta.hasLore()) {
            initLoreStats(item);
        }
        List<String> lore = itemMeta.getLore();
        for (int i=0;i<lore.size();i++) {
            String str = lore.get(i);
            if (str.contains("공격력:")) {
                Matcher matcherNum = patternNum.matcher(str);
                this.power = Integer.parseInt(str.substring(matcherNum.start(), matcherNum.end()));
                getLogger().info("공격력"+"::: "+str.substring(matcherNum.start(), matcherNum.end()));
            } else if (str.contains("크리확률:")) {
                Matcher matcherNum = patternNum.matcher(str);
                this.criticalPercent = Integer.parseInt(str.substring(matcherNum.start(), matcherNum.end()));
            } else if (str.contains("크리데미지:")) {
                Matcher matcherNum = patternNum.matcher(str);
                this.critical = Integer.parseInt(str.substring(matcherNum.start(), matcherNum.end()));
            } else if (str.contains("체력:")) {
                Matcher matcherNum = patternNum.matcher(str);
                this.health = Integer.parseInt(str.substring(matcherNum.start(), matcherNum.end()));
            } else if (str.contains("피흡:")) {
                Matcher matcherNum = patternNum.matcher(str);
                this.drain = Integer.parseInt(str.substring(matcherNum.start(), matcherNum.end()));
            } else if (str.contains("방어력:")) {
                Matcher matcherNum = patternNum.matcher(str);
                this.defend = Integer.parseInt(str.substring(matcherNum.start(), matcherNum.end()));
            } else if (str.contains("방어력무시:")) {
                Matcher matcherNum = patternNum.matcher(str);
                this.ignoreDefend = Integer.parseInt(str.substring(matcherNum.start(), matcherNum.end()));
            } else {
                return null;
            }

        }
        return this;
    }

    public ArrayList<String> toLoreList() {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            res.add(this.getStatsNames().get(i) + ": " + this.getPowerString());
        }
        return res;
    }

    public int getPower() {
        return power;
    }
}

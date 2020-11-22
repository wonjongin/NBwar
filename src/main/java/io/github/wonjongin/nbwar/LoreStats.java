package io.github.wonjongin.nbwar;

import lombok.*;
import org.bukkit.entity.Item;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.SplittableRandom;

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
    private int power; // 공격력
    private int criticalPercent; // 크리 확률
    private int critical; // 크리 데미지
    private int health; // 체력
    private int drain; // 피흡
    private int defend; // 방어력
    private int ignoreDefend; // 방어력 무시
    private String powerString = String.valueOf(power); // 공격력
    private String criticalPercentString= String.valueOf(criticalPercent);; // 크리 확률
    private String criticalString= String.valueOf(critical);; // 크리 데미지
    private String healthString= String.valueOf(health);; // 체력
    private String drainString= String.valueOf(drain);; // 피흡
    private String defendString= String.valueOf(defend);; // 방어력
    private String ignoreDefendString= String.valueOf(ignoreDefend);; // 방어력 무시
    private final String[] statsNames = {
            "공격력",
            "크리확률",
            "크리데미지",
            "체력",
            "피흡",
            "방어력",
            "방어력무시",
    };
    private int[] allLore = {
            power,
            criticalPercent,
            critical,
            health,
            drain,
            defend,
            ignoreDefend
    };
    public LoreStats ParseToLoreStats(Item item){
        ItemMeta itemMeta = item.getItemStack().getItemMeta();
        List<String> lore = itemMeta.getLore();
        for (int i = 0; i < lore.size(); i++) {
            String str = lore.get(i);
            for (int j = 0; j < this.statsNames.length; j++) {
                if(str.equals(this.statsNames[j])){

                }
            }
            
        }
        return this;
    }

}

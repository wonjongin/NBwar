package io.github.wonjongin.nbwar;

import org.bukkit.entity.Player;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@Getter
@Setter
@RequiredArgsConstructor
public class PlayerStats {
    private int power; // 공격력
    private static double health; // 체력
}

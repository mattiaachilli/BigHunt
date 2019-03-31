package view.scenecontroller;

import java.util.Map;

import model.achievements.Achievement;
import model.achievements.AchievementType;

/**
 * interface for update achievements.
 *
 */
public interface AchievementSceneController {
    /**
     * update game achievements info.
     * @param achievements
     *          achievements of the player
     */
    void setAchievements(Map<AchievementType, Achievement> achievements);
}

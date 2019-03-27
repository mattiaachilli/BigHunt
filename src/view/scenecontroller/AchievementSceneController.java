package view.scenecontroller;

import java.util.List;

import model.achievements.Achievement;

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
    public void setAchievements(final List<Achievement> achievements);
}

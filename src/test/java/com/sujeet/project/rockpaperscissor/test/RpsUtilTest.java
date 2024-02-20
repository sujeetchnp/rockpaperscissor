package com.sujeet.project.rockpaperscissor.test;

import com.sujeet.project.rockpaperscissor.model.Choice;
import com.sujeet.project.rockpaperscissor.model.GameResult;
import com.sujeet.project.rockpaperscissor.util.RpsUtil;
import org.junit.Assert;
import org.junit.Test;

public class RpsUtilTest {

    @Test
    public void testDraw(){
        GameResult gameResult = RpsUtil.compareChoice(Choice.ROCK, Choice.ROCK);
        Assert.assertEquals(GameResult.DRAW, gameResult);

        gameResult = RpsUtil.compareChoice(Choice.SCISSOR, Choice.SCISSOR);
        Assert.assertEquals(GameResult.DRAW, gameResult);

        gameResult = RpsUtil.compareChoice(Choice.PAPER, Choice.PAPER);
        Assert.assertEquals(GameResult.DRAW, gameResult);
    }

    @Test
    public void testWin(){
        GameResult gameResult = RpsUtil.compareChoice(Choice.PAPER, Choice.ROCK);
        Assert.assertEquals(GameResult.WIN, gameResult);

        gameResult = RpsUtil.compareChoice(Choice.ROCK, Choice.SCISSOR);
        Assert.assertEquals(GameResult.WIN, gameResult);

        gameResult = RpsUtil.compareChoice(Choice.SCISSOR, Choice.PAPER);
        Assert.assertEquals(GameResult.WIN, gameResult);

    }

    @Test
    public void testLose(){
        GameResult gameResult = RpsUtil.compareChoice(Choice.ROCK, Choice.PAPER);
        Assert.assertEquals(GameResult.LOSE, gameResult);

        gameResult = RpsUtil.compareChoice(Choice.SCISSOR, Choice.ROCK);
        Assert.assertEquals(GameResult.LOSE, gameResult);

        gameResult = RpsUtil.compareChoice(Choice.PAPER, Choice.SCISSOR);
        Assert.assertEquals(GameResult.LOSE, gameResult);

    }

}

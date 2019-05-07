package com.example.botb;

import static org.mockito.ArgumentMatchers.*;
import static org.powermock.api.mockito.PowerMockito.when;

import android.util.Base64;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.weapon.ExampleWeapon;
import com.example.botb.model.weapon.Weapon;
import java.io.IOException;
import org.junit.*;
import org.junit.runner.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Base64.class})
public class ParserTest {

    @Test
    public void actionToString() throws IOException {
        PowerMockito.mockStatic(Base64.class);
        when(Base64.encode(any(), anyInt())).thenAnswer(
                invocation -> java.util.Base64.getEncoder().encode((byte[]) invocation.getArguments()[0]));
        when(Base64.decode(anyString(), anyInt())).thenAnswer(
                invocation -> java.util.Base64.getMimeDecoder().decode((String) invocation.getArguments()[0]));

        Weapon weapon = new ExampleWeapon();
        Location location = new Location(150, 150);
        Action action = new Action(location, weapon);

        String actionString = Parser.actionToString(action);
    }

    @Test
    public void boardToString() throws IOException {
        PowerMockito.mockStatic(Base64.class);
        when(Base64.encode(any(), anyInt())).thenAnswer(
                invocation -> java.util.Base64.getEncoder().encode((byte[]) invocation.getArguments()[0]));
        when(Base64.decode(anyString(), anyInt())).thenAnswer(
                invocation -> java.util.Base64.getMimeDecoder().decode((String) invocation.getArguments()[0]));

        Board board = new Board(15, 15);

        String boardToString = Parser.boardToString(board);

    }

    @Test
    public void stringToAction() {
    }

    @Test
    public void stringToBoard() {
    }
}
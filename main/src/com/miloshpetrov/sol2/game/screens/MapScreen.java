package com.miloshpetrov.sol2.game.screens;

import com.badlogic.gdx.Input;
import com.miloshpetrov.sol2.SolCmp;
import com.miloshpetrov.sol2.game.MapDrawer;
import com.miloshpetrov.sol2.game.SolGame;
import com.miloshpetrov.sol2.ui.*;

import java.util.ArrayList;
import java.util.List;

public class MapScreen implements SolUiScreen {
  private final List<SolUiControl> myControls;
  private final SolUiControl myCloseCtrl;
  public final SolUiControl zoomInCtrl;
  public final SolUiControl zoomOutCtrl;

  public MapScreen(RightPaneLayout rightPaneLayout) {
    myControls = new ArrayList<SolUiControl>();

    myCloseCtrl = new SolUiControl(rightPaneLayout.buttonRect(1), Input.Keys.TAB, Input.Keys.ESCAPE);
    myCloseCtrl.setDisplayName("Close");
    myControls.add(myCloseCtrl);
    zoomInCtrl = new SolUiControl(rightPaneLayout.buttonRect(2), Input.Keys.UP);
    zoomInCtrl.setDisplayName("Zoom In");
    myControls.add(zoomInCtrl);
    zoomOutCtrl = new SolUiControl(rightPaneLayout.buttonRect(3), Input.Keys.DOWN);
    zoomOutCtrl.setDisplayName("Zoom Out");
    myControls.add(zoomOutCtrl);
  }

  @Override
  public List<SolUiControl> getControls() {
    return myControls;
  }

  @Override
  public void updateCustom(SolCmp cmp, SolInputMan.Ptr[] ptrs) {
    SolGame g = cmp.getGame();
    boolean justClosed = myCloseCtrl.isJustOff();
    MapDrawer mapDrawer = g.getMapDrawer();
    mapDrawer.setToggled(!justClosed);
    if (justClosed) {
      cmp.getInputMan().setScreen(cmp, g.getScreens().mainScreen);
    }
    boolean zoomIn = zoomInCtrl.isJustOff();
    if (zoomIn || zoomOutCtrl.isJustOff()) {
      mapDrawer.changeZoom(zoomIn);
    }
    float mapZoom = mapDrawer.getZoom();
    zoomInCtrl.setEnabled(mapZoom != MapDrawer.MIN_ZOOM);
    zoomOutCtrl.setEnabled(mapZoom != MapDrawer.MAX_ZOOM);
  }

  @Override
  public void drawPre(UiDrawer uiDrawer, SolCmp cmp) {
  }

  @Override
  public boolean isCursorOnBg(SolInputMan.Ptr ptr) {
    return false;
  }

  @Override
  public void onAdd(SolCmp cmp) {

  }

  @Override
  public void drawPost(UiDrawer uiDrawer, SolCmp cmp) {
  }
}
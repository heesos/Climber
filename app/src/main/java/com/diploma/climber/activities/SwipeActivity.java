package com.diploma.climber.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import com.diploma.climber.R;
import com.diploma.climber.cards.CardStackAdapter;
import com.diploma.climber.cards.CardStackCallback;
import com.diploma.climber.domain.IdHolder;
import com.diploma.climber.domain.User;
import com.diploma.climber.domain.usersRelations.UserUserCrossEntity;
import com.diploma.climber.viewModel.UserViewModel;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SwipeActivity extends AppCompatActivity {

    private CardStackAdapter adapter;
    private CardStackLayoutManager manager;
    private UserViewModel userViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        CardStackView cardStackView = findViewById(R.id.card_stack_view);

        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {

            }

            @Override
            public void onCardSwiped(Direction direction) {
                if (direction == Direction.Right) {
                    if(manager.getTopPosition() == adapter.getItemCount()) {
                        // TUTAJ NIE DZIALA A MA SIE POKAZAC
                        // INNY LAYOUT ZE JUZ KURWA NIE MA NIC
                    }
                    Toast.makeText(SwipeActivity.this, "Added to contacts", Toast.LENGTH_LONG)
                            .show();
                    User addedUser = adapter.getUsers().get(manager.getTopPosition() - 1);
                    int loggedId = IdHolder.getInstance().getData();
                    addRelations(loggedId, addedUser.getId());
                }

            }

            @Override
            public void onCardRewound() {

            }

            @Override
            public void onCardCanceled() {

            }

            @Override
            public void onCardAppeared(View view, int position) {

            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        });

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());

        adapter = new CardStackAdapter(addList());

        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void addRelations(int loggedId, int id) {
        UserUserCrossEntity userUserCrossEntity = new UserUserCrossEntity();
        userUserCrossEntity.setFirstUserId(loggedId);
        userUserCrossEntity.setSecondUserId(id);

        userViewModel.insertUserRelations(userUserCrossEntity);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void nextCard() {
        List<User> old = adapter.getUsers();
        List<User> baru = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<User> addList() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        List<User> users = userViewModel.getAllUsers();
        Optional<User> userToRemove = users.stream()
                .filter(user -> user.getId() == IdHolder.getInstance().getData())
                .findFirst();
        users.remove(userToRemove.get());
        return users;
    }
}
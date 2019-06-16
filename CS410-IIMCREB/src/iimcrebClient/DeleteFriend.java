package iimcrebClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteFriend extends JFrame {
    DeleteFriend(ClientController cc){
        setTitle("Delete a friend");
        JPanel delpnl = new JPanel();
        JLabel delfrlable = new JLabel("User name");
        delpnl.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JTextField usernamet = new JTextField(10);
        JButton delfrbut = new JButton("delete");

        delfrbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.delFriend(usernamet.getText());
            }
        });

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weighty = 0.5;
        gc.weightx = 0.5;

        delpnl.add(delfrlable, gc);

        gc.gridx = 1;
        gc.gridy = 0;

        delpnl.add(usernamet, gc);

        gc.gridy = 2;

        delpnl.add(delfrbut, gc);


        add(delpnl);
        setPreferredSize(new Dimension(200, 150));
        pack();
        setVisible(true);

    }
}

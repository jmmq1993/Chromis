/*
**    Chromis POS  - The New Face of Open Source POS
**    Copyright (c)2015-2016
**    http://www.chromis.co.uk
**
**    This file is part of Chromis POS Version V0.60.2 beta
**
**    Chromis POS is free software: you can redistribute it and/or modify
**    it under the terms of the GNU General Public License as published by
**    the Free Software Foundation, either version 3 of the License, or
**    (at your option) any later version.
**
**    Chromis POS is distributed in the hope that it will be useful,
**    but WITHOUT ANY WARRANTY; without even the implied warranty of
**    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**    GNU General Public License for more details.
**
**    You should have received a copy of the GNU General Public License
**    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>
**
**
*/

package uk.chromis.pos.reports;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.chromis.basic.BasicException;
import uk.chromis.data.gui.ComboBoxValModel;
import uk.chromis.data.loader.Datas;
import uk.chromis.data.loader.QBFCompareEnum;
import uk.chromis.data.loader.SentenceList;
import uk.chromis.data.loader.SerializerWrite;
import uk.chromis.data.loader.SerializerWriteBasic;
import uk.chromis.pos.forms.AppLocal;
import uk.chromis.pos.forms.AppView;
import uk.chromis.pos.forms.DataLogicSales;
import uk.chromis.pos.sync.DataLogicSync;

public class JParamsLocation extends javax.swing.JPanel implements ReportEditorCreator {

    private SentenceList m_sentlocations;
    private ComboBoxValModel m_LocationsModel;
    private String siteGuid;
    private DataLogicSales dlSales;

    public JParamsLocation() {
        initComponents();
    }

    @Override
    public void init(AppView app) {
        DataLogicSync dlSync = (DataLogicSync) app.getBean("uk.chromis.pos.sync.DataLogicSync");
        dlSales = (DataLogicSales) app.getBean("uk.chromis.pos.forms.DataLogicSales");

        this.siteGuid = dlSync.getSiteGuid();

        m_sentlocations = dlSales.getLocationsList(siteGuid);
        m_LocationsModel = new ComboBoxValModel();
    }

    public void refreshList(String siteGuid) {
        m_sentlocations = dlSales.getLocationsList(siteGuid);
        m_LocationsModel = new ComboBoxValModel();

        List a;
        try {
            a = m_sentlocations.list();
            addFirst(a);
            m_LocationsModel = new ComboBoxValModel(a);
            m_LocationsModel.setSelectedFirst();
            m_jLocation.setModel(m_LocationsModel); // refresh model
        } catch (BasicException ex) {
            Logger.getLogger(JParamsLocation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void activate() throws BasicException {
        List a = m_sentlocations.list();
        addFirst(a);
        m_LocationsModel = new ComboBoxValModel(a);
        m_LocationsModel.setSelectedFirst();
        m_jLocation.setModel(m_LocationsModel); // refresh model   
    }

    @Override
    public SerializerWrite getSerializerWrite() {
        return new SerializerWriteBasic(new Datas[]{Datas.OBJECT, Datas.STRING});
    }

    @Override
    public Component getComponent() {
        return this;
    }

    protected void addFirst(List a) {
        // do nothing
    }

    public void addActionListener(ActionListener l) {
        m_jLocation.addActionListener(l);
    }

    public void removeActionListener(ActionListener l) {
        m_jLocation.removeActionListener(l);
    }

    @Override
    public Object createValue() throws BasicException {

        return new Object[]{
            m_LocationsModel.getSelectedKey() == null ? QBFCompareEnum.COMP_NONE : QBFCompareEnum.COMP_EQUALS,
            m_LocationsModel.getSelectedKey()
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_jLocation = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, AppLocal.getIntString("label.bywarehouse"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setPreferredSize(new java.awt.Dimension(370, 60));

        m_jLocation.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText(AppLocal.getIntString("label.locationname")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel8;
    public javax.swing.JComboBox m_jLocation;
    // End of variables declaration//GEN-END:variables

}

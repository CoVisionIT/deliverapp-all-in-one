package be.covisionit.deliverapp;

import be.covisionit.deliverapp.proto.*;
import com.google.protobuf.Timestamp;

import java.util.*;

public final class TestData {

    public static DespatchAdvice buildDespatchAdvice()  {

        String orderreference = "Bestelbon 1218 - Dossier 283";
        String bonnummer = "83067";

        String suppliername = "Stra";
        String supplierid = "BE06";
        String supplierphonenumber = "01355";

        String customername = "Be";
        String customerid = "BE04";
        String customerphonenumber = "";

        String sitereference = "Wegeniswerken Veld";
        String deliveryaddressstreetname = "Straat";
        String deliveryaddresscityname = "Zemst";
        String deliveryaddresspostalzone = "1960";
        String deliveryaddresscountrycode = "BE";

        String transporterName = "V Transport";
        String transporterLicensePlate = "1WD9";
        Date date = new GregorianCalendar(2019, Calendar.JUNE, 6, 11, 50).getTime();
        Timestamp despatchDateTime = toTimestamp(date);

        DeliveryNoteReceptionPreferences.EmailPreferences.Builder emailPreferences =
                DeliveryNoteReceptionPreferences.EmailPreferences.newBuilder();
        emailPreferences.setActive(true);
        emailPreferences.setEmailAddress("be-transport@m.com");
        emailPreferences.setFormat(DeliveryNoteReceptionPreferences.DeliveryNoteFormat.JSON);

        DeliveryNoteReceptionPreferences.EndpointPreferences.Builder endpointPreferences =
                DeliveryNoteReceptionPreferences.EndpointPreferences.newBuilder();
        endpointPreferences.setActive(false);
        endpointPreferences.setUrl("");
        endpointPreferences.setFormat(DeliveryNoteReceptionPreferences.DeliveryNoteFormat.JSON);

        Despatch.Builder despatch = Despatch.newBuilder()
                .setDespatchAddress(Address.newBuilder()
                        .setID(sitereference)
                        .setStreetName(deliveryaddressstreetname)
                        .setPostalZone(deliveryaddresspostalzone)
                        .setCityName(deliveryaddresscityname)
                        .setCountry(deliveryaddresscountrycode)
                );
        despatch.setActualDespatchTimestamp(despatchDateTime);

        DespatchAdvice levering = DespatchAdvice.newBuilder()
                .setOrderReference(OrderReference.newBuilder()
                        .setID(orderreference)
                )
                .setDespatchSupplierParty(DespatchSupplierParty.newBuilder()
                        .setParty(Party.newBuilder()
                                .setPartyIdentification(PartyIdentification.newBuilder()
                                        .setID(supplierid)
                                )
                                .setPartyName(PartyName.newBuilder()
                                        .setName(suppliername)
                                )
                                .setContact(Contact.newBuilder()
                                        .setTelephone(supplierphonenumber)
                                )
                        )
                        .setDeliveryNoteReceptionPreferences(DeliveryNoteReceptionPreferences.newBuilder()
                                .setEmailPreferences(emailPreferences)
                                .setEndpointPreferences(endpointPreferences)
                        )
                )
                .setDeliveryCustomerParty(DeliveryCustomerParty.newBuilder()
                        .setParty(Party.newBuilder()
                                .setPartyIdentification(PartyIdentification.newBuilder()
                                        .setID(customerid)
                                )
                                .setPartyName(PartyName.newBuilder()
                                        .setName(customername)
                                )
                                .setContact(Contact.newBuilder()
                                        .setTelephone(customerphonenumber)
                                )
                        )
                )
                .setShipment(Shipment.newBuilder()
                        .setID(bonnummer)
                        .setConsignment(Consignment.newBuilder()
                                .setInformation(transporterLicensePlate)
                                .setCarrierParty(Party.newBuilder()
                                        .setPartyName(PartyName.newBuilder()
                                                .setName(transporterName)
                                        )
                                )
                        )
                        .setDelivery(Delivery.newBuilder()
                                .setDespatch(despatch)
                        )
                )
                .build();

        return levering;
    }

    public static Timestamp toTimestamp(Date date) {
        long millis = date.getTime();
        return Timestamp.newBuilder()
                        .setSeconds(millis / 1000)
                        .setNanos((int) ((millis % 1000) * 1000000))
                        .build();
    }

}

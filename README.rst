java-en16931
============

.. image:: https://travis-ci.org/invinet/java-en16931.svg?branch=master
    :target: https://travis-ci.org/invinet/java-en16931

.. image:: https://codecov.io/gh/invinet/java-en16931/branch/master/graph/badge.svg
   :target: https://codecov.io/gh/invinet/java-en16931

Java Library to read, write and manage the new `EN16931 Invoice format <http://docs.peppol.eu/poacc/billing/3.0/bis/>`_.

This `European Standard <https://standards.cen.eu/dyn/www/f?p=204:110:0::::FSP_PROJECT:60602&cs=1B61B766636F9FB34B7DBD72CE9026C72>`_ establishes a semantic data model of the core elements of an electronic invoice. The semantic model includes only the essential information elements that an electronic invoice needs to ensure legal (including fiscal) compliance and to enable interoperability for cross-border, cross sector and for domestic trade.

Features
--------

This library allows you to:

1. De-serialize an XML in EN16931 format to a python Invoice object.
2. Serialize a python Invoice object to a valid XML representation.
3. Validate an Invoice using `validex <https://open.validex.net>`_.
4. Import an Invoice to `B2BRouter <https://www.b2brouter.net/>`_.

Documentation
-------------

You can see the javadoc documentation at our `web page <https://invinet.github.io/java-en16931/>`_.

Limitations
-----------

This is a proof of concept implementation and not all features defined
in the EN16931 standard are implemented. But it is easy, in some cases
trivial, to implement them. The main not implemented features are:

* CreditNotes are not supported.
* File attachments are not supported.
* Delivery information is not supported.
* Only global charges and discounts are supported. Line discounts and
  charges are not supported.
* Other potentially useful attributes (such as InvoicePeriod, BuyerReference,
  OrderReference, BillingReference, ContractDocumentReference, among others)
  are not implemented.

If you need a particular feature implemented, see the following section
for feature requests.

Bugs and Feature Requests
-------------------------

Please report any bugs that you find `here <https://github.com/invinet/java-en16931/issues>`_.
Or, even better, fork the repository on `GitHub <https://github.com/invinet/java-en16931>`_
and create a pull request (PR). We welcome all changes, big or small.

License
-------

Released under the Apache License Version 2.0 (see `LICENSE.txt`)::

    Copyright (C) 2018 Invinet Sistemes

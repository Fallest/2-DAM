FROM php:7.1.20-apache

# Other PHP7 Extensions
RUN docker-php-ext-install pdo_mysql 
RUN docker-php-ext-install mysqli
# Enable apache modules
RUN a2enmod rewrite headers

FROM redis:alpine

COPY wait-for .
RUN ["chmod", "+x", "./wait-for"]

EXPOSE 6379
